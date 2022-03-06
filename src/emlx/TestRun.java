package emlx;

import java.io.IOException;

public class TestRun {

	public static void main(String[] args) {
		try {
			Emlx emlx = new Emlx("202083.emlx");
			System.out.println("length of email: " + emlx.getEmailLen());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
