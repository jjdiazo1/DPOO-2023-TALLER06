package procesamiento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Combo;
import modelo.Ingrediente;
import modelo.IngredienteRepetidoException;
import modelo.ProductoMenu;
import modelo.ProductoRepetidoException;

public class LoaderRestaurante {

	public static Map<Integer, ProductoMenu> cargarMenu(String file) throws IOException, ProductoRepetidoException {
        Map<String, Integer> productNames = new HashMap<>();
        Map<Integer, ProductoMenu> menu = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int id = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
                int price = Integer.parseInt(parts[1]);

                if (productNames.containsKey(name)) {
                    throw new ProductoRepetidoException(name);
                }

                productNames.put(name, id);
                ProductoMenu item = new ProductoMenu(name, price);
                menu.put(id, item);
                id++;
            }
        } catch (IOException e) {
            throw e;
        }

        return menu;
    }

    public static Map<Integer, Ingrediente> cargarIngredientes(String file) throws IOException, IngredienteRepetidoException {
        Map<String, Integer> ingredientNames = new HashMap<>();
        Map<Integer, Ingrediente> ingredients = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int id = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String name = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
                int additionalCost = Integer.parseInt(parts[1]);

                if (ingredientNames.containsKey(name)) {
                    throw new IngredienteRepetidoException(name);
                }

                ingredientNames.put(name, id);
                Ingrediente ingredient = new Ingrediente(name, additionalCost);
                ingredients.put(id, ingredient);
                id++;
            }
        } catch (IOException e) {
            throw e;
        }

        return ingredients;
    }


	private static Map<Integer, Combo> cargarCombos(List<ProductoMenu> baseMenu, List<ProductoMenu> beverages, String file)
		    throws IOException {
		    Map<Integer, Combo> combos = new HashMap<>();
		    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
		        String line;
		        int id = 1;
		        while ((line = reader.readLine()) != null) {
		            String[] parts = line.split(";");
		            String comboName = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
		            double discount = Double.parseDouble(parts[1].replace('%', '\u0000'));
		            Combo combo = new Combo(comboName, discount);
		            for (int i = 2; i < parts.length; i++) {
		                String productName = parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1);
		                ProductoMenu product = buscarProducto(baseMenu, beverages, productName);
		                combo.agregarItemACombo(product);
		            }
		            combos.put(id, combo);
		            id++;
		        }
		    } catch (FileNotFoundException e) {
		        throw e;
		    }
		    return combos;
		}

	public static Restaurante cargarArchivos(String menuFile, String beveragesFile, String ingredientsFile, String combosFile)
		    throws FileNotFoundException, IOException, ProductoRepetidoException, IngredienteRepetidoException {
		    Map<Integer, ProductoMenu> baseMenu = cargarMenu(menuFile);
		    Map<Integer, ProductoMenu> drinks = cargarMenu(beveragesFile);
		    Map<Integer, Ingrediente> ingredients = cargarIngredientes(ingredientsFile);
		    Map<Integer, Combo> comboList = cargarCombos(new ArrayList<>(baseMenu.values()), new ArrayList<>(drinks.values()), combosFile);
		    return new Restaurante(ingredients, baseMenu, drinks, comboList);
		}


	private static ProductoMenu buscarProducto(List<ProductoMenu> baseMenu, List<ProductoMenu> drinks, String productName) {
	    ProductoMenu selectedProduct = null;

	    for (int i = 0; i < baseMenu.size() && selectedProduct == null; i++) {
	        ProductoMenu product = baseMenu.get(i);
	        if (product.getNombre().equals(productName)) {
	            selectedProduct = product;
	        }
	    }

	    for (int i = 0; i < drinks.size() && selectedProduct == null; i++) {
	        ProductoMenu product = drinks.get(i);
	        if (product.getNombre().equals(productName)) {
	            selectedProduct = product;
	        }
	    }

	    return selectedProduct;
	}


}
