package ec.inventario.ws.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Transient;

import ec.inventario.ws.excepcion.ServicioExcepcion;
import lombok.Getter;

@PersistenceContext(name = DaoImpl.INVENTARIO_PU, unitName = DaoImpl.INVENTARIO_PU)
public class DaoImpl<T> {

    protected static final String INVENTARIO_PU = "inventario-pu";

    @Transient
    @Resource
    @Getter
    private EJBContext ejbContext;

    private Class<T> clase;

    public DaoImpl() {

    }

    public DaoImpl(Class<T> clazz) {
        this.clase = clazz;
    }

    protected EntityManager getEntityManager() {
        return (EntityManager) ejbContext.lookup(DaoImpl.INVENTARIO_PU);
    }

    public T obtenerPorLlavePrimaria(Object id) {
        return getEntityManager().find(clase, id);
    }

    public void guardar(T t) throws ServicioExcepcion {
        try {

            getEntityManager().merge(t);
            getEntityManager().flush();
        } catch (RuntimeException e) {
            throw new ServicioExcepcion(e);
        }
    }

    @SuppressWarnings({ "hiding", "rawtypes", "unchecked" })
	public <T> List<T> listarPorSentenciaSql(final Query query, final Class claseRetorno) throws ServicioExcepcion {
        try {
            return (List<T>) ConvertirSqlObjectUtil.retornar(query.getResultList(), claseRetorno);
        } catch (RuntimeException e) {
            throw new ServicioExcepcion(e);
        }
    }
}
