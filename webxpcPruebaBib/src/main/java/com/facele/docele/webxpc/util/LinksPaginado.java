package com.facele.docele.webxpc.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.facele.facilito.dto.v10.xpc.LinkType;

public class LinksPaginado {

	public static LinksPaginado newInstancia() {
		return new LinksPaginado();
	}

	/**
	 * 
	 * @param sizeRegistros     cantidad de registros de consulta actual
	 * @param currentRequestUri URL de actual request
	 * @return List<LinkType>
	 */
	public List<LinkType> setPaginado(int sizeRegistros, ServletUriComponentsBuilder currentRequestUri) {
		List<LinkType> result = new ArrayList<>();

		LinkType linkCurrent = new LinkType();
		linkCurrent.setHref(currentRequestUri.toUriString());
		linkCurrent.setRel("current");
		result.add(linkCurrent);

		Integer offset;
		if (currentRequestUri.build().getQueryParams().containsKey("offset")) {
			String str = currentRequestUri.build().getQueryParams().get("offset").get(0);
			offset = Integer.parseInt(str.isEmpty() ? "0" : str);

		} else
			offset = 0;

		Integer limit;
		if (currentRequestUri.build().getQueryParams().containsKey("limit")) {
			String str = currentRequestUri.build().getQueryParams().get("limit").get(0);
			limit = Integer.parseInt(str.isEmpty() ? "0" : str);

		} else
			limit = 100;

		Integer offsetPrevios = offset - limit;
		if (offsetPrevios.compareTo(Integer.valueOf(0)) < 0)
			offsetPrevios = 0;

		Integer offsetNext = offset + limit;

		LinkType linkPrev = new LinkType();
		linkPrev.setHref(currentRequestUri.replaceQueryParam("offset", offsetPrevios.toString()).toUriString());
		linkPrev.setRel("prev");

		LinkType linkNext = new LinkType();
		linkNext.setHref(currentRequestUri.replaceQueryParam("offset", offsetNext.toString()).toUriString());
		linkNext.setRel("next");

		if (sizeRegistros == 0 && offset.intValue() == 0)
			return result;

		else if (offset.intValue() > 0 && sizeRegistros == 0)
			result.add(linkPrev);

		else if (offset.intValue() > 0 && sizeRegistros < limit.intValue())
			result.add(linkPrev);

		else if (offset.intValue() == 0 && sizeRegistros == limit.intValue())
			result.add(linkNext);

		else if (offset.intValue() > 0 && sizeRegistros == limit.intValue()) {
			result.add(linkPrev);
			result.add(linkNext);
		}

		return result;
	}
}
