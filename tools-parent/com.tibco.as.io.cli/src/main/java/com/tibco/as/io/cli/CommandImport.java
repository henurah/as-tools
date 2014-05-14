package com.tibco.as.io.cli;

import com.beust.jcommander.Parameter;
import com.tibco.as.io.Import;
import com.tibco.as.io.Operation;
import com.tibco.as.space.Member.DistributionRole;

public abstract class CommandImport extends Command {

	@Parameter(description = "Distribution role (none, leech, seeder)", names = { "-distribution_role" }, converter = DistributionRoleConverter.class, validateWith = DistributionRoleConverter.class)
	private DistributionRole distributionRole;

	@Parameter(description = "Space operation (get, load, none, partial, put, take)", names = { "-operation" }, converter = OperationTypeConverter.class, validateWith = OperationTypeConverter.class)
	private Operation operation;

	@Parameter(description = "Wait for ready timeout", names = { "-wait_for_ready_timeout" })
	private Long waitForReadyTimeout;

	public void configure(Import transfer) {
		super.configure(transfer);
		transfer.setDistributionRole(distributionRole);
		transfer.setOperation(operation);
		transfer.setWaitForReadyTimeout(waitForReadyTimeout);
	}
}
