package edu.eci.arsw.blueprints.filtros.implementacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
@Qualifier("Muestreo")
public class FiltroSubmuestreo implements Filtro{

	@Override
	public Blueprint filtrar(Blueprint bp) {
		List<Point> points = bp.getPoints();
		List<Point> temp = new ArrayList<>();
		boolean delete = false;
		for (int i = 0; i < points.size(); i++) {
			if(delete) {
				delete = false;
			}else {
				temp.add(points.get(i));
				delete = true;
			}
		}
		Point[] p = new Point[temp.size()];
		p = temp.toArray(p);
		
		return new Blueprint(bp.getAuthor(), bp.getName(), p);
	}

}
