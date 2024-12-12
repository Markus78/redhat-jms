package jms;

import jakarta.annotation.Resource;
import jakarta.ejb.EJBContext;
import jakarta.ejb.Stateless;
import jakarta.jms.JMSException;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Queue;
import jakarta.jms.QueueConnection;
import jakarta.jms.QueueConnectionFactory;
import jakarta.jms.QueueSender;
import jakarta.jms.QueueSession;
import jakarta.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class JMSSender {

	private static final Logger LOG = LoggerFactory.getLogger(SenderResource.class);

	@Resource
	private EJBContext ejbCtx;

	@Resource(name = JNDILocalNames.ELIGCR_QUEUE_CONNECTION_FACTORY)
	private QueueConnectionFactory eligCRQueueConnectionFactory;
	@Resource(name = JNDILocalNames.ELIGCR_QUEUE)
	private Queue eligCRQueue;

	public void sendMessage(int id) {

		try (QueueConnection con = eligCRQueueConnectionFactory.createQueueConnection();
				QueueSession session = con.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
				QueueSender sender = session.createSender(eligCRQueue)) {
			con.start();
			ObjectMessage objectMessage = session.createObjectMessage();

			// Send
			objectMessage.setObject(new MyMessage("hello", id));
			sender.send(objectMessage);
			session.commit();
		} catch (JMSException e) {
			ejbCtx.setRollbackOnly();
			LOG.error("sender exception", e);
		}
	}
}
