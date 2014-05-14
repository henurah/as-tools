package com.tibco.as.io.cli;

import java.util.Collection;

import com.beust.jcommander.Parameter;
import com.tibco.as.io.IMetaspaceTransfer;
import com.tibco.as.io.IMetaspaceTransferListener;
import com.tibco.as.io.ITransfer;
import com.tibco.as.io.Transfer;
import com.tibco.as.io.TransferException;
import com.tibco.as.space.Metaspace;

public abstract class Command implements IMetaspaceTransferListener {

	@Parameter(description = "Transfer output batch size", names = { "-batch_size" })
	private Integer batchSize;
	@Parameter(description = "Number of writer threads", names = { "-writer_thread_count" })
	private Integer workerCount;

	public void configure(Transfer transfer) {
		transfer.setBatchSize(batchSize);
		transfer.setWorkerCount(workerCount);
	}

	public void execute(Metaspace metaspace) {
		Collection<IMetaspaceTransfer> transfers = getMetaspaceTransfers(metaspace);
		for (IMetaspaceTransfer transfer : transfers) {
			transfer.addListener(this);
			try {
				transfer.execute();
			} catch (TransferException e) {
				String message = e.getLocalizedMessage();
				if (e.getCause() != null) {
					message += ": " + e.getCause().getLocalizedMessage();
				}
				System.err.println(message);
			}
		}
	}

	protected abstract Collection<IMetaspaceTransfer> getMetaspaceTransfers(
			Metaspace metaspace);

	@Override
	public void opening(Collection<ITransfer> transfers) {
		System.out.println(getExecutingMessage(transfers));
	}

	@Override
	public void executing(ITransfer transfer) {
		transfer.addListener(new TransferListener(transfer) {

			@Override
			protected String getOpenedMessage(ITransfer transfer) {
				return Command.this.getOpenedMessage(transfer);
			}

			@Override
			protected String getClosedMessage(ITransfer transfer) {
				return Command.this.getClosedMessage(transfer);
			}

		});
	}

	protected abstract String getExecutingMessage(
			Collection<ITransfer> transfers);

	protected abstract String getOpenedMessage(ITransfer transfer);

	protected abstract String getClosedMessage(ITransfer transfer);

	public void prepare() throws Exception {
	}

}
