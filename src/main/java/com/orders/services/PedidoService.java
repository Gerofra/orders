package com.orders.services;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.entities.Pedido;
import com.orders.entities.Producto;
import com.orders.entities.Usuario;
import com.orders.enums.PedidoEnum;
import com.orders.errors.ErrorService;
import com.orders.repos.PedidoRepo;
import com.orders.repos.ProductoRepo;
import com.orders.repos.UsuarioRepo;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepo pedidoRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Transactional
    public void agregar(Map<String, String> params) throws ErrorService {

        Pedido pedido = new Pedido();
        String id_productos = "";
        String cant_productos = "";

        for (Map.Entry<String, String> entry : params.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());

            String key = entry.getKey();
            String value = entry.getValue();

            /*AGREGO EL USUARIO AL PEDIDO*/
            if (key.equalsIgnoreCase("user")) {
                Optional<Usuario> respuestaUsuario = usuarioRepo.findById(value);

                if (respuestaUsuario.isPresent()) {
                    pedido.setUsuario(usuarioRepo.getById(value));
                }
            }

            if (key.matches("[+-]?\\d*(\\.\\d+)?")) {
                if (productoRepo.findById((long) Integer.parseInt(key)).isPresent() && Integer.parseInt(value) != 0) {

                    id_productos += key + "-";
                    cant_productos += value + "-";

                }
            }
        }


        /*QUITO EL ULTIMO GUION*/
        id_productos = id_productos.substring(0, id_productos.length() - 1);
        cant_productos = cant_productos.substring(0, cant_productos.length() - 1);

        pedido.setId_productos(id_productos);
        pedido.setCant_productos(cant_productos);
        pedido.setFechaPedido(new Date());
        pedido.setEstado(PedidoEnum.PEDIDO_ACTIVO);

        /*

		pedido.setTotal(total);
         */
        pedidoRepo.save(pedido);

    }

    @Transactional
    public void modificar(Usuario usuario, String id_productos, String cant_productos, Date fechaEntrega, String total, String id) throws ErrorService {

        Optional<Pedido> respuesta = pedidoRepo.findById(id);

        if (respuesta.isPresent()) {
            Pedido pedido = respuesta.get();
            pedido.setUsuario(usuario);
            pedido.setId_productos(id_productos);
            pedido.setCant_productos(cant_productos);
            pedido.setFechaPedido(new Date());
            pedido.setFechaEntrega(fechaEntrega);
            pedido.setTotal(total);

            pedidoRepo.save(pedido);

        } else {
            throw new ErrorService("No se encontró el pedido solicitado.");
        }

    }

    @Transactional
    public Map<Producto, String> listadoProductos(String id) throws ErrorService {

        Map<Producto, String> productos = new LinkedHashMap<>();

        if (id != null) {
            Pedido pedido = pedidoRepo.getById(id);

            String[] id_productos = pedido.getId_productos().split("-");
            String[] cant_productos = pedido.getCant_productos().split("-");

            int cont = 0;
            for (String id_producto : id_productos) {

                Optional<Producto> respuesta = productoRepo.findById(Long.valueOf(id_producto));

                if (respuesta.isPresent()) {
                    Producto producto = respuesta.get();
                    productos.put(producto, cant_productos[cont]);
                    cont++;
                }
            }

            productos.remove(null);
            System.out.print(productos.toString());
            return productos;
        }
        return null;
    }

    @Transactional
    public Pedido mostrarPedido(String id) throws ErrorService {

        if (id != null) {
            Pedido pedido = pedidoRepo.getById(id);
            return pedido;
        }
        return null;
    }

    @Transactional
    public void entregado(String id) throws ErrorService {

        Optional<Pedido> respuesta = pedidoRepo.findById(id);

        if (respuesta.isPresent()) {
            Pedido pedido = respuesta.get();
            pedido.setFechaEntrega(new Date());
            pedidoRepo.save(pedido);

        } else {
            throw new ErrorService("No se encontró el pedido solicitado.");
        }
    }

    @Transactional
    public void agregarDetalle(String id, String detalle) throws ErrorService {

        Optional<Pedido> respuesta = pedidoRepo.findById(id);

        if (respuesta.isPresent()) {
            Pedido pedido = respuesta.get();
            pedido.setDetalle(detalle);
            pedidoRepo.save(pedido);

        } else {
            throw new ErrorService("No se encontró el pedido solicitado.");
        }
    }

    @Transactional
    public void cambiarEstado(String id, String estado) throws ErrorService {

        Optional<Pedido> respuesta = pedidoRepo.findById(id);

        if (respuesta.isPresent()) {
            Pedido pedido = respuesta.get();
            if (estado.equalsIgnoreCase("activo")) {
                pedido.setEstado(PedidoEnum.PEDIDO_ACTIVO);
            } else if (estado.equalsIgnoreCase("cerrado")) {
                pedido.setEstado(PedidoEnum.PEDIDO_CERRADO);
                entregado(id);
            } else if (estado.equalsIgnoreCase("revision")) {
                pedido.setEstado(PedidoEnum.PEDIDO_REVISION);
            }

            pedidoRepo.save(pedido);

        } else {
            throw new ErrorService("No se encontró el pedido solicitado.");
        }
    }

    public List<Producto> buscarPorUsuario(String nombre) {
        return pedidoRepo.listarPorUsuario(nombre + "%");
    }

    public List<Pedido> listaPedidosQ(String q) {
        return pedidoRepo.buscarPorQ("%" + q + "%");
    }

    @Transactional
    public void delete(String id) {
        Optional<Pedido> respuesta = pedidoRepo.findById(id);
        if (respuesta.isPresent()) {
            pedidoRepo.delete(respuesta.get());
        }
    }

}
