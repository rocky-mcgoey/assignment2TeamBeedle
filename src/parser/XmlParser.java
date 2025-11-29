package parser;

import java.io.Console;
import java.io.IOException;
import java.util.List;

import exceptions.EmptyQueueException;
import implementations.MyQueue;
import implementations.MyStack;

public class XmlParser {
	MyStack<String> stack = new MyStack<String>();
	MyQueue<String> errorQ = new MyQueue<String>();
	MyQueue<String> extrasQ = new MyQueue<String>();
	XmlTagReader reader = new XmlTagReader();

	public void parse(String file) throws IOException, EmptyQueueException {
		List<XmlLine> lines = reader.readXmlFile(file);

		for (XmlLine line : lines) {
			String newLine = line .toString();
			char lastChar = newLine.charAt(newLine.length() - 1);
			char firstChar = newLine.charAt(0);
			
			if (lastChar == '/') continue;
			if (firstChar != '/') stack.push(newLine.replaceFirst("/", ""));
			
			if (newLine.substring(1) == stack.peek()) stack.pop();
			else if (newLine.substring(1) == errorQ.peek()) errorQ.dequeue();
			else if (stack.size() == 0) errorQ.enqueue(newLine.replaceFirst("/", ""));
			else {
				if (stack.contains(newLine)) {
					while (stack.peek() != newLine) {
					String popped = stack.pop();
					errorQ.enqueue(popped);
					System.out.println(popped);
					}
				}
				else extrasQ.enqueue(newLine);
			}
		}
		
		while (stack.size() > 0) errorQ.enqueue(stack.pop());
		
		if ((errorQ.size() == 0 || extrasQ.size() == 0) && !(errorQ.size() == 0 && extrasQ.size() == 0)) {
			while (errorQ.size() > 0) {
				String dequeued = errorQ.dequeue();
				System.out.println(dequeued);
			}
			while (extrasQ.size() > 0) {
				String dequeued = errorQ.dequeue();
				System.out.println(dequeued);
			}
		}
		
		while (!(errorQ.size() == 0 && extrasQ.size() == 0)) {
			if (errorQ.peek() != extrasQ.peek()) {
				String dequeued = errorQ.dequeue();
				System.out.println(dequeued);
			}
			else {
				errorQ.dequeue();
				extrasQ.dequeue();
			}
		}
		
	}

	public MyStack<String> getStack() {
		return stack;
	}

	public MyQueue<String> getErrorQ() {
		return errorQ;
	}

	public MyQueue<String> getExtrasQ() {
		return extrasQ;
	}
}
