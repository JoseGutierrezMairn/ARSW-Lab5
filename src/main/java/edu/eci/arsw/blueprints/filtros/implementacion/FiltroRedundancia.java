package edu.eci.arsw.blueprints.filtros.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

//@Qualifier("Redundancia")
@Service("Redundancia")
public class FiltroRedundancia implements Filtro {

	@Override
	public Blueprint filtrar(Blueprint bp) {
		List<Point> points = bp.getPoints();
		List<Point> filtrado = new ArrayList<Point>();
		Point anterior = null;
		for(Point p : points) {
			if((anterior == null)|| (anterior != null && (p.getX() != anterior.getX() || p.getY() != anterior.getY()))){
				filtrado.add(p);
			}
			anterior = p;
		}
		Point[] def = new Point[filtrado.size()];
		def = filtrado.toArray(def);
		return new Blueprint (bp.getAuthor(), bp.getName(), def);
	}

}
