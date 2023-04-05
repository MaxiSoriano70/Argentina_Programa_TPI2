package Modelos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Resultados {
	private ArrayList<Ronda> rondas = new ArrayList<Ronda>();
	private String ruta;

	public Resultados(String ruta) {
		this.ruta=ruta;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public ArrayList<Ronda> getRondas() {
		return rondas;
	}

	public void setRondas(ArrayList<Ronda> rondas) {
		this.rondas = rondas;
	}

	public void setResultados(){
		try {
			for(String linea:Files.readAllLines(Paths.get(this.ruta))) {
				int auxRonda=Integer.parseInt(linea.split(";")[0]);
				Ronda ronda=new Ronda(auxRonda);
				if(rondas.contains(ronda)) {
					int lugarDeLaRonda = rondas.indexOf(ronda);
					Ronda rondaselecionada=rondas.get(lugarDeLaRonda);
					String local=linea.split(";")[1];
					String visitante=linea.split(";")[4];
					int golesLocal=Integer.valueOf(linea.split(";")[2]);
					int golesVisitante=Integer.valueOf(linea.split(";")[3]);
					Partido partido=setPartido(local,visitante,golesLocal,golesVisitante);
					rondaselecionada.getPartidos().add(partido);
				}
				else {
					rondas.add(ronda);
					String local=linea.split(";")[1];
					String visitante=linea.split(";")[4];
					int golesLocal=Integer.valueOf(linea.split(";")[2]);
					int golesVisitante=Integer.valueOf(linea.split(";")[3]);
					Partido partido=setPartido(local,visitante,golesLocal,golesVisitante);
					ronda.getPartidos().add(partido);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Partido setPartido(String local,String visitante,int golesLocal,int golesVisitante) {
		Equipo l=new Equipo(local);
		Equipo v=new Equipo(visitante);
		Partido partido=new Partido(l,v);
		if(golesLocal>golesVisitante) {
			partido.setResultado(EGanador.LOCAL);
		}else if(golesLocal<golesVisitante) {
			partido.setResultado(EGanador.VISITANTE);
		}
		else {
			partido.setResultado(EGanador.EMPATE);
		}
		return partido;
	}
	
	public void mostrar_Rondas() {
		ArrayList<Ronda> rondas = this.getRondas();
		for(int i=0;i<rondas.size();i++) {
			System.out.print("Ronda "+rondas.get(i).getNumero()+" ");
			rondas.get(i).mostrar_Resultados();
		}
	}
	
	
}