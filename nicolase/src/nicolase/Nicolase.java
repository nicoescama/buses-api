/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nicolase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author nicoescama
 */
public class Nicolase {
       
    public static Map<Character, Long> getCaracteresRepetidos(String palabra){
        
        //chars that are not alphanumeric are removed
        palabra = palabra.replaceAll("[^a-zA-Z0-9]", ""); 
                
        Map<Character, Long> valores = palabra.toLowerCase().chars().mapToObj(e -> (char)e).
                collect(Collectors.groupingBy(c -> c, Collectors.counting())).entrySet().stream()
                .filter(c -> c.getValue() > 1).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        return valores;        
    }
    
    public static Set<String> filtrar(Set<String> palabrasfiltrar, String filtroA){
    	//filter is modified to simulate contains method using matches method
    	String filtroT = ".*"+ filtroA+ ".*";
    	return palabrasfiltrar.stream().filter(p ->p.matches(filtroT)).collect(Collectors.toSet());        
    }


    public static void main(String[] args) {
        
        Set<String> palabras = new HashSet<>();
        palabras.add("Nicolas");
        palabras.add("Eliana");
        palabras.add("Jorge12");
        palabras.add("@@@Prueba1");
        palabras.add("##@@");
        
        
        //When sorted it is needed to be saved on a List to keep the order
        //the method sorted() can have input of type Comparator in order to define the way of comparing items 
        List<String> palabras_ordenadas = palabras.stream().sorted()
        		.collect(Collectors.toList());
        
        String filtro = "[^a-zA-Z0-9]";
        //To filter it is used the created method filtrar
        //its input can be a pattern or a regex
        Set<String> palabras_filtradas = filtrar(palabras, filtro);
        
        System.out.println(palabras_filtradas);
        System.out.println(palabras_ordenadas);
        
        Map<Character, Long> respuesta = getCaracteresRepetidos("EliAAaAa$$a@@@@na11");
        System.out.println(respuesta);
        
        
        
        
        
    }
    
}
