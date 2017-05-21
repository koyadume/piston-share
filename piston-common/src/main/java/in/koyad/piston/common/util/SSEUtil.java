/*
 * Copyright (c) 2012-2017 Shailendra Singh <shailendra_01@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.koyad.piston.common.util;

import java.io.PrintWriter;

/**
 * 
 * 
 * @author shailendra
 */
public class SSEUtil {
	
	public static void sendMessage(PrintWriter writer, String msg, String event) {
		StringBuilder eventMsg = new StringBuilder();
		eventMsg.append("event:").append(event).append("\ndata:").append(msg).append("\n\n");
		
		/*
		 * Only one thread out of several threads 
		 * should write to response at a time for a request otherwise it may result in
		 * corrupted event messages.
		 */
		synchronized (writer) {
			writer.write(eventMsg.toString());
			writer.flush();
		}
	}

}
