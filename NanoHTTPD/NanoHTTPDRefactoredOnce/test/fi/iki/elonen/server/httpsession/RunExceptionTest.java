package fi.iki.elonen.server.httpsession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import fi.iki.elonen.server.BasicServer;

public class RunExceptionTest extends HTTPSessionBase {

	@Before
	public void init() throws Exception {
		String sillyGetRequestStringWithTwoParms = "GET /?thing1=one&thing2=two\r\n";
		mockSocket = createSocket(sillyGetRequestStringWithTwoParms);
	}

	@Test
	public void canSendErrorResponseFor_handleSingleSession_ThatBlowsUp() throws IOException {
		ourSession = new HTTPSession(mockSocket) {
			protected void handleSingleSession() throws Exception  {
				throw new Exception("Mocking exception to test try/catch code");
			}
			
			protected void sendInternalServerError(Exception e) throws Exception  {
			}
		};

		try {
			ourSession.run();
			fail("Did not throw excepted exception.");
		} catch (Exception e) {
			assertEquals("java.lang.Exception: Mocking exception to test try/catch code", e.getMessage());
		}
	}
	
	@Test
	public void canCatchErrorSentBy_SendResponse() {
		ourSession = new HTTPSession(mockSocket) {
			protected void handleSingleSession() throws Exception {
				sendError(BasicServer.HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: IOException: " + "Testing response to sendError() that blows up.");
			}
			
			protected void sendError(String status, String msg) throws Exception{
				throw new Error("Mocking error to test try/catch code");
			}
		};

		try {
			ourSession.run();
			fail("Failed to catch Error thrown by sendError().");
		} catch (Throwable t) {
			assertEquals("Mocking error to test try/catch code", t.getMessage());
		}
	}

}
