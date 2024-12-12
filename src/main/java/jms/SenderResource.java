package jms;

import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("message")
public class SenderResource {

	private static final Logger LOG = LoggerFactory.getLogger(SenderResource.class);

	@Inject
	JMSSender sender;

	@POST
	public void post(@DefaultValue("1") @QueryParam("number") int number) {
		for (int i = 0; i < number; i++) {
			LOG.info("send message");
			sender.sendMessage(i);
		}
	}

	@GET
	public String get() {
		LOG.info("get message");
		return "hello";
	}
}
