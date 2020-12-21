package com.ricbap.salvavidas.api.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.util.StringUtils;

import com.ricbap.salvavidas.api.SalvavidasApiApplication;
import com.ricbap.salvavidas.api.model.Grupo;
import com.ricbap.salvavidas.api.storage.S3;

public class GrupoAnexoListener {
	
	@PostLoad
	public void postLoad(Grupo grupo) {
		if(StringUtils.hasText(grupo.getAnexo())) {
			S3 s3 = SalvavidasApiApplication.getBean(S3.class);
			grupo.setAnexoUrl(s3.configurarUrl(grupo.getAnexo()));
		}
		
	}

}
