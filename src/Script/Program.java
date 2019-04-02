package Script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

public class Program 
{
	
	public static void main(String[] args) throws IOException 
	{
		//classe JFileChooser para o usu�rio selecionar o seu path
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
		     String pasta=jfc.getSelectedFile().getAbsoluteFile().toString();            
		}
		String pasta=jfc.getSelectedFile().getAbsoluteFile().toString(); 
		File root = new File(pasta);
		String path = (pasta +"lista.txt"); // aqui se vc desejar armazenar a lista em excel use a extens�o ".csv"
		List<File> files = search(root, "wav", "flac", "aiff", "mp3");   //coloque aqui as extens�es desejadas
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			for(File f : files) 
		{
				bw.write(f.getName());
				bw.newLine();
		}
         }
         
	}
	public static List<File> search(File root, String ... extensions) 
	{
		List<File> resultado = new ArrayList<>();
		
		for(File f : root.listFiles())
		{
			if(f.isDirectory()) //condi��o para armazenar as subpastas
			{
				resultado.addAll(search(f, extensions)); 
			} else 
			  {
				if(chechExtension(f, extensions))    //checagem do arquivo se est� nas extens�es descritas acima.
				 // verifica a extens�o do arquivo
					resultado.add(f);	
			  }
		}
		
		return resultado;
	}
	
	public static boolean chechExtension(File file, String [] extensions) 
	{
		for(String extension : extensions) 
		{
			if(file.getName().endsWith(extension)) 
			{
				return true;
			}
		}
		
		return false;
	}
}