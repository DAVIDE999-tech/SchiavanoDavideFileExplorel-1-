import java.io.*;

public class FileProva {
private String fileIn,fileOut;
	public FileProva(String nf,String fileO) {
		fileIn=nf;
		fileOut=fileO;
		
	}
	public String leggiFile() {
		String s="";
		try {
			FileReader f=new FileReader(fileIn);
			BufferedReader fin=new BufferedReader(f);
			String riga=null;
			while((riga=fin.readLine())!=null)
				s+=riga+"\n";
			f.close();
		}catch(Exception e) {
			System.out.println("errore:"+e);
		}
		
		return s;
	}
	public void scriviFile(String testo) {
		try {
			FileWriter f=new FileWriter(fileOut);
			PrintWriter fout=new PrintWriter(f);
			fout.println(testo);
			fout.close();
			f.close();
		}catch(Exception e) {
			System.err.println("errore"+e);
		}
	}
	public static void main(String[] args) {
		FileProva fp=new FileProva("prova.txt","prova.txt");
		
		fp.scriviFile("Questa è una prova\nCiao");
		System.out.println(fp.leggiFile());
	}
}