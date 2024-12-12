package jms;

public final class JNDILocalNames {

	// QUEUE
	public static final String ELIGCR_QUEUE = "java:/jms/EligCRQueue";
	public static final String LISTS_QUEUE = "java:/jms/ListsQueue";
	public static final String COLLECT_STATISTICS_QUEUE = "java:/jms/CollectStatisticsQueue";

	// QUEUE CONNECTION FACTORY
	public static final String ELIGCR_QUEUE_CONNECTION_FACTORY = "java:/jms/EligCRQCF";
	public static final String LISTS_QUEUE_CONNECTION_FACTORY = "java:/jms/ListsQueueCF";
	public static final String COLLECT_STATISTICS_QUEUE_CONNECTION_FACTORY = "java:/jms/CollectStatisticsQueueCF";

	private JNDILocalNames() {
	}
}