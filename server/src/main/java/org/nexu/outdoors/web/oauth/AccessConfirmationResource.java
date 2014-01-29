package org.nexu.outdoors.web.oauth;

import org.nexu.outdoors.web.model.AuthorizationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.DefaultAuthorizationRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.util.LinkedHashMap;
import java.util.Map;


public class AccessConfirmationResource {

    @Autowired
	private ClientDetailsService clientDetailsService;


    @Path("confirm_access")
    @GET
	public AuthorizationResponse getAccessConfirmation(@Context HttpServletRequest req, @Context javax.ws.rs.core.SecurityContext contextl) throws Exception {
        HttpSession session= req.getSession(true);
        AuthorizationRequest clientAuth = (AuthorizationRequest) session.getAttribute("authorizationRequest");
		ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
		Map<String, String> scopes = new LinkedHashMap<String, String>();
		/*
        for (String scope : clientAuth.getScope()) {
			scopes.put(OAuth2Utils.SCOPE_PREFIX + scope, "false");
		}
		for (Approval approval : approvalStore.getApprovals(principal.getName(), client.getClientId())) {
			if (clientAuth.getScope().contains(approval.getScope())) {
				scopes.put(OAuth2Utils.SCOPE_PREFIX + approval.getScope(),
						approval.getStatus() == ApprovalStatus.APPROVED ? "true" : "false");
			}
		}
		model.put("scopes", scopes);
		*/
		return new AuthorizationResponse(client, (DefaultAuthorizationRequest) clientAuth, "");
	}

}
