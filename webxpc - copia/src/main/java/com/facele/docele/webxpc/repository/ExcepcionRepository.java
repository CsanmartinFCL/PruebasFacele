package com.facele.docele.webxpc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facele.docele.webxpc.domain.Abonado;
import com.facele.docele.webxpc.domain.Excepcion;
import com.facele.docele.webxpc.domain.Mid;
import com.facele.docele.webxpc.domain.Motivo;
import com.facele.docele.webxpc.domain.Sid;
import com.facele.docele.webxpc.domain.Uid;

public interface ExcepcionRepository extends JpaRepository<Excepcion, Long>{

	Optional<Excepcion> findByMidAndUidAndSidAndAbonadoAndMotivo(final Mid mid, final Uid uid, final Sid sid,
			final Abonado abonado, final Motivo motivo);

}
