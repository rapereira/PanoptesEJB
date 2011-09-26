package br.edu.nsi.iff.teste;

import java.sql.Timestamp;
import java.util.Random;

import org.joda.time.DateTime;

import br.edu.nsi.iff.Leitura;
import br.edu.nsi.iff.controller.PontosmontagenJPAController;

public class TestePopular {

	public static void main(String[] args){
		
		TestePopular.popularinstance();
	}

	public static void firstinstance(){
		TestePlanta.insert();
		TesteArea.insert();
		TesteEquipamento.insert();
		TesteComponente.insert();
		TesteSensor.insert();
		TesteMontagem.insert();
		TestePonto.popularinsert();
		TestePontosmontagen.popularinsert();
	}

	public static void popularinstance(){

		Timestamp datahora = new Timestamp(System.currentTimeMillis());
		Timestamp datahorainicio = new Timestamp(System.currentTimeMillis());
		DateTime datetime = new DateTime();	
		
		for(int numberleitura = 1; numberleitura <= 15; numberleitura++){
			datahora = new Timestamp(datetime.getMillis());
			
			for (int numberponto = 1; numberponto <= 16; numberponto++){
				PontosmontagenJPAController instancePontosmontagen = new PontosmontagenJPAController();
				
				String uid = Integer.toString(numberponto);
				Leitura leitura = TesteLeitura.popularinsert(instancePontosmontagen.find(uid), datahora);
				System.out.println("insert LEITURA" + numberleitura + "  PONTO" + numberponto);

				Random generator = new Random();
				int random = generator.nextInt(10) + 15;
				for (int numberpico = 1 ; numberpico <= random; numberpico++){
					TestePico.popularinsert(leitura);
					System.out.println("insert PICO" + numberpico);
				}
			}
			
			datetime = datetime.plusMinutes(4);
		}

		Timestamp datahorafim = new Timestamp(System.currentTimeMillis());
		System.out.println("\n\n\n" + datahorainicio.toString() + "\n" + datahorafim.toString());
	}
}
