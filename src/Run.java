import java.io.File;

public class Run {

	public static void main(String[] args) {
		Converter cn = new Converter(new File("HLA-B_Intron_exon_data.txt"), new File("HLA-B_Intron_exon_data_reformat.txt"));
		cn.execute();

	}

}
