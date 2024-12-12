package jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "8"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/EligCRQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
@TransactionManagement(TransactionManagementType.BEAN)
public class MessageConsumerBean implements MessageListener {

	private static final Logger LOG = LoggerFactory.getLogger(MessageConsumerBean.class);

	/**
	 * ATTENTION: This method is NOT allowed to throw any type of exceptions or errors back to the queue. It is also NOT
	 * allowed to participate in or start a transaction due to JMS and Queue strategy described in SAD for this batch (All
	 * messages consumed by this method must be treated as a successful call from the message listener no matcher what
	 * happens below! - We don't want poison messages returned batch to the queue and the mechanism for that is to always
	 * return void). In other words only catch all exceptions and log.
	 * 
	 * @param msg ObjectMessage containing object AsyncEligCRQueueVO
	 * @see jakarta.jms.MessageListener#onMessage(jakarta.jms.Message)
	 */
	@Override
	public void onMessage(Message msg) {

		try {
			ObjectMessage objectMessage = (ObjectMessage) msg;
			MyMessage message = (MyMessage) objectMessage.getObject();

			LOG.info("message received: {} id {}", message.getDescription(), message.getId());

		} catch (Exception e) {
			// Just log, nothing more to do or allowed
			LOG.error("MDB error, objectMessage: {} ", msg, e);
		}
	}
}
