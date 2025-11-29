package parser;

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

			// I dunno if doing the one liner if statements is okay or not, but I'm doing it here to remind myself I can
			if (lastChar == '/') continue;
			if (firstChar != '/') stack.push(newLine.replaceFirst("/", ""));


			if (!stack.isEmpty() && newLine.substring(1).equals(stack.peek())) {
				stack.pop();
			} else if (!errorQ.isEmpty() && newLine.substring(1).equals(errorQ.peek())) {
				errorQ.dequeue();
			} else if (stack.size() == 0)	{
				errorQ.enqueue(newLine.replaceFirst("/", ""));
			}	else {
				if (stack.contains(newLine)) {
					while (!stack.isEmpty() && !stack.peek().equals(newLine)) {
						String popped = stack.pop();
						errorQ.enqueue(popped);
						System.out.println(popped);
					}
				}	else {
					extrasQ.enqueue(newLine);
				}
			}
		}

		while (stack.size() > 0) {
			errorQ.enqueue(stack.pop());
		}

		if ((errorQ.size() == 0 || extrasQ.size() == 0) && !(errorQ.size() == 0 && extrasQ.size() == 0)) {
			while (errorQ.size() > 0) {
				String dequeued = errorQ.dequeue();
				System.out.println(dequeued);
			}

			while (extrasQ.size() > 0) {
				String dequeued = extrasQ.dequeue();
				System.out.println(dequeued);
			}
		}

		while (!(errorQ.size() == 0 && extrasQ.size() == 0)) {
			if (!errorQ.isEmpty() && !extrasQ.isEmpty() && !errorQ.peek().equals(extrasQ.peek())) {
				String dequeued = errorQ.dequeue();
				System.out.println(dequeued);
			} else {
				if (!errorQ.isEmpty()) errorQ.dequeue();
				if (!extrasQ.isEmpty()) extrasQ.dequeue();
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

	/**
	 * Main method to run parser... Hopefully this is where it can go?
	 *
	 * @param args command line arguments - expects XML file path
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: java parser.XmlParser <xml-file>");
			System.out.println("Example: java parser.XmlParser res/sample1.xml");
			return;
		}

		String filename = args[0];
		XmlParser parser = new XmlParser();

		System.out.println("Parsing XML file: " + filename);
		System.out.println();

		try {
			parser.parse(filename);
			System.out.println("\nParsing complete.");
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		} catch (EmptyQueueException e) {
			System.err.println("Error during parsing: " + e.getMessage());
		}
	}
}
