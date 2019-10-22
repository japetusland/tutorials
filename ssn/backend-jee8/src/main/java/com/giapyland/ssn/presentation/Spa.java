package com.giapyland.ssn.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.giapyland.ssn.business.PostsManager;
import com.giapyland.ssn.business.SsnSecurityService;
import com.giapyland.ssn.business.model.PostDto;
import com.giapyland.ssn.presentation.api.model.Post;
import com.giapyland.ssn.presentation.model.SpaInitialData;

@Named
@RequestScoped
public class Spa {

	@Inject
	private SsnSecurityService securityService;

	@Inject
	private PostsManager postsManager;
	
	@Inject
	private FacesContext facesContext;

	private SpaInitialData initialData;

	public SpaInitialData getInitialData() {
		return initialData;
	}

	public void setInitialData(SpaInitialData initialData) {
		this.initialData = initialData;
	}

	private SpaInitialData loadData(String loggedUser) {
		List<String> friends = securityService.getUsers();
		friends.remove(loggedUser);

		List<PostDto> postDtos = postsManager.getPosts(loggedUser);
		List<Post> posts = new ArrayList<Post>();
		for (PostDto postDto : postDtos)
			posts.add(new Post(postDto));

		return new SpaInitialData(loggedUser, friends, posts);
	}

	@PostConstruct
	public void onload() {
		String userLogged = facesContext.getExternalContext().getUserPrincipal().getName();
		setInitialData(loadData(userLogged));
	}
	
	public String goToAngular() {
		return "/angular/index.xhtml?faces-redirect=true";
	}

	public String goToReact() {
		return "/react/index.xhtml?faces-redirect=true";
	}
}
