package emlx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Emlx {
	
	public Emlx(String fileName) throws IOException {
		this.fileName = fileName;
		readEmlx();
	}
	
	String fileName;
	
	int numCharsInEmlx = 0;
	String entireEmlx; // email with the plist at the end
	int emailLen;  // length of the email without the plist
	StringBuffer pList; // just the plist info

	int flags;
	
	// within the emlx is the length of the email and the plist at the end that isn't accounted for
	// in the number of characters at the beginning
	private void readEmlx() throws IOException {
		Path filePath = Paths.get("202083.emlx");
		String entireEmlx = Files.readString(filePath);
		System.out.println(entireEmlx);
		
		StringTokenizer strTokens = new StringTokenizer(entireEmlx);
//		String firstString = strTokens.nextToken();
	
		String emailLenStr = strTokens.nextToken();
				
		emailLen = Integer.parseInt(emailLenStr);
		
	}
	
	int getEmailLen() {
		return emailLen;
	}
	
	
	
	
	public enum FlagMasks {
		
		READ(0b1),
		DELETED(0b10),
		ANSWERED(0b100),
		ENCRYPTED(0b1000),
		FLAGGED(0b10000),
		RECENT(0b100000),
		DRAFT(0b1000000),
		INITIAL(0b10000000),
		FORWARDED(0b100000000),
		REDIRECTED(0b1000000000),
		ATTACHMENT_COUNT(0x3C00),
		PRIORITY_LEVEL(0x7F0000),
		SIGNED(0b100000000000000000000000),
		IS_JUNK(0b1000000000000000000000000),
		IS_NOT_JUNK(0b10000000000000000000000000),
		FONT_SIZE_DELTA(0b11100000000000000000000000000), // bits 26-28
		JUNK_MAIL_LEVEL_RECORDED(0b100000000000000000000000000000),
		HIGHLIGHT_TEXT_IN_TOC(0B1000000000000000000000000000000),
		NOT_USED(0B10000000000000000000000000000000);
	
		private int value;
		
		private FlagMasks(int value) {
			this.value = value;
		}

	}
	public boolean isRead() 		{ return((flags & FlagMasks.READ.value) > 0); 		}
	public boolean isDeleted() 		{ return((flags & FlagMasks.DELETED.value)> 0 );	}
	public boolean isAnswered() 	{ return((flags & FlagMasks.ANSWERED.value) > 0);	}
	public boolean isEncrypted() 	{	return((flags & FlagMasks.FLAGGED.value)> 0 );	}
	public boolean isRecent() 		{ return((flags & FlagMasks.RECENT.value)> 0 );		}
	public boolean isDraft() 		{ return((flags & FlagMasks.DRAFT.value)> 0);		}
	public boolean isInitial()	 	{	return((flags & FlagMasks.INITIAL.value) > 0);	}
	public boolean isForwarded( ) 	{ return((flags & FlagMasks.FORWARDED.value)>0); 	}
	public boolean isRedirected() 	{ return((flags & FlagMasks.REDIRECTED.value)>0); 	}
	public int attachmentCount() {
		int flags2 = flags;
		int attchmntCount = flags2 >>> 10;
		return(attchmntCount);
	}
	public int priorityLevel() {
		int flags2 = flags;
		int priLvl= flags2 >>> 16;
		return(priLvl);
	}
	public boolean isSigned() 		{ return ((flags & FlagMasks.SIGNED.value)> 0); 		}
	public boolean isJunk()			{ return ((flags & FlagMasks.IS_JUNK.value)> 0); 		}
	public boolean isNotJunk()		{ return ((flags & FlagMasks.IS_NOT_JUNK.value) > 0); 	}
	public int fontSizeDelta()	{
		int flags2 = flags;
		int fntSzDlta = flags2 >>> 26;
		return(fntSzDlta);
	}
	public boolean junkMailLvl() 		{ return ((flags & FlagMasks.JUNK_MAIL_LEVEL_RECORDED.value)> 0); 	}
	public boolean highlightTxtInTOC() 	{ return ((flags & FlagMasks.HIGHLIGHT_TEXT_IN_TOC.value)>0); 		}
	public boolean notUsed()			{ return ((flags & FlagMasks.NOT_USED.value)>0);					}
	
}
