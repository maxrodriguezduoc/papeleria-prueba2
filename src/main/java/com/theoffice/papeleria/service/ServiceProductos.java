package com.theoffice.papeleria.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.ProductosDTO;
import com.theoffice.papeleria.model.Productos;
import com.theoffice.papeleria.repository.ProductosRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceProductos {
    @Autowired
    private ProductosRepository productosRepository;

    public List<ProductosDTO> obtenerTodos(){
        return productosRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ProductosDTO buscarPorId(Integer id) {
        Productos productos = productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡Producto no encontrado!"));
        return convertirADTO(productos);
    }

    public String eliminar(Integer id) {
        try {
            Productos producto = productosRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El producto con ID " + id + " no existe."));
            productosRepository.delete(producto);
            return "El producto '" + producto.getNombre_producto() + "' ha sido eliminado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Productos guardarProducto(Productos producto) {
        return productosRepository.save(producto);
    }

    public Productos actualizarProducto(Integer id, Productos producto) {
        Productos prod = productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡El producto no existe!"));

        // Actualizar atributos básicos
        if (producto.getNombre_producto() != null) {
            prod.setNombre_producto(producto.getNombre_producto());
        }
        if (producto.getPrecio_producto() != null) {
            prod.setPrecio_producto(producto.getPrecio_producto());
        }
        return productosRepository.save(prod);
    }


    
    private ProductosDTO convertirADTO(Productos productos){
        ProductosDTO productosDTO = new ProductosDTO();
        productosDTO.setId_productos(productos.getId_productos());
        productosDTO.setNombre_producto(productos.getNombre_producto());
        productosDTO.setPrecio_producto(productos.getPrecio_producto());
        return productosDTO;
    }

}   







