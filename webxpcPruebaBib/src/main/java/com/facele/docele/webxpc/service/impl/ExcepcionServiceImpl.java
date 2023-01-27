package com.facele.docele.webxpc.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.facele.docele.webxpc.domain.Abonado;
import com.facele.docele.webxpc.domain.Excepcion;
import com.facele.docele.webxpc.domain.Licencia;
import com.facele.docele.webxpc.domain.Mid;
import com.facele.docele.webxpc.domain.Motivo;
import com.facele.docele.webxpc.domain.Sid;
import com.facele.docele.webxpc.domain.Uid;
import com.facele.docele.webxpc.repository.ExcepcionRepository;
import com.facele.docele.webxpc.repository.IExcepcionDAO;
import com.facele.docele.webxpc.service.AbonadoService;
import com.facele.docele.webxpc.service.ArchivoService;
import com.facele.docele.webxpc.service.ExcepcionService;
import com.facele.docele.webxpc.service.LicenciaService;
import com.facele.docele.webxpc.service.MidService;
import com.facele.docele.webxpc.service.MotivoService;
import com.facele.docele.webxpc.service.SidService;
import com.facele.docele.webxpc.service.UidService;
import com.facele.docele.webxpc.util.SearchCriteria;
import com.facele.docele.webxpc.util.SearchCriteria.OPERATION;

import io.facele.facilito.dto.v10.xpc.EstadoExcepcion;
import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;

@Service
public class ExcepcionServiceImpl implements ExcepcionService {

	private IExcepcionDAO excepcionDAO;

	private MidService midService;
	private UidService uidService;
	private SidService sidService;
	private AbonadoService abonadoService;
	private MotivoService motivoService;
	private LicenciaService licenciaService;
	private ArchivoService archivoService;
	private ExcepcionRepository excepcionRepository;

	@Autowired
	public ExcepcionServiceImpl(final IExcepcionDAO excepcionDAO, final MidService midService,
			final UidService uidService, SidService sidService, final AbonadoService abonadoService,
			final MotivoService motivoService, final LicenciaService licenciaService,
			final ArchivoService archivoService, final ExcepcionRepository excepcionRepository) {
		this.excepcionDAO = excepcionDAO;
		this.midService = midService;
		this.uidService = uidService;
		this.sidService = sidService;
		this.abonadoService = abonadoService;
		this.motivoService = motivoService;
		this.excepcionRepository = excepcionRepository;
		this.licenciaService = licenciaService;
		this.archivoService = archivoService;
	}

	@Override
	public List<Excepcion> consultar(Long motivoId, Long midId, Long sidId, Long abonadoId, Long licenciaId,
			String fechaEventoDesde, String fechaEventoHasta, Integer limit, Integer offset) {

		List<SearchCriteria> params = new ArrayList<SearchCriteria>();

		if (motivoId != null) {
			params.add(new SearchCriteria("motivo", OPERATION.equal, motivoId, null));
		}
		if (midId != null) {
			params.add(new SearchCriteria("mid", OPERATION.equal, midId, null));
		}
		if (sidId != null) {
			params.add(new SearchCriteria("sid", OPERATION.equal, sidId, null));
		}
		if (abonadoId != null) {
			params.add(new SearchCriteria("abonado", OPERATION.equal, abonadoId, null));
		}
		if (licenciaId != null) {
			params.add(new SearchCriteria("licencia", OPERATION.equal, licenciaId, null));
		}
		if (fechaEventoDesde != null) {
			params.add(new SearchCriteria("fechaEventoDesde", OPERATION.greaterThanOrEqualTo, fechaEventoDesde, null));
		}
		if (fechaEventoHasta != null) {
			params.add(new SearchCriteria("fechaEventoHasta", OPERATION.lessThanOrEqualTo, fechaEventoHasta, null));
		}
		return excepcionDAO.consultar(params, PageRequest.of(offset, limit));
	}

	@Override
	public Excepcion obetener(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Excepcion crear(InputExcepcionCrear input) {

		Mid mid = midService.crear(input.getNodo().getMID());
		Uid uid = uidService.crear(input.getNodo().getUID());
		Sid sid = sidService.crear(input.getNodo().getSID());
		Abonado abonado = abonadoService.obtener(input.getAbonadoIdentificacion());
		Motivo motivo = motivoService.crear(input.getMotivo().getDescripcion());
		Licencia licencia = licenciaService.crear(input.getLicenciaIdentificacion());

		Optional<Excepcion> _excepcion = excepcionRepository.findByMidAndUidAndSidAndAbonadoAndMotivo(mid, uid, sid,
				abonado, motivo);
		Excepcion excepcion;

		if (_excepcion.isPresent()) {
			excepcion = _excepcion.get();
			excepcion.setCantidadEventos(excepcion.getCantidadEventos() + 1);
		} else {
			excepcion = new Excepcion();
			excepcion.setAbonado(abonado);
			excepcion.setCantidadEventos(0);
			excepcion.setEstado(EstadoExcepcion.INFORMADO);
			excepcion.setFechaEvento(LocalDateTime.now());
			excepcion.setFechaRegistro(LocalDateTime.now());
			excepcion.setId(null);
			excepcion.setLicencia(licencia);
			excepcion.setMid(mid);
			excepcion.setMotivo(motivo);
			excepcion.setSid(sid);
			excepcion.setUid(uid);
		}
		excepcion =excepcionRepository.save(excepcion);
		archivoService.crear(input.getThrowable(),excepcion);

		return excepcion;
	}

}
