package com.neev.moh.facade;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.neev.moh.model.User;

@Component("MohWebserviceFacade")
public interface MohWebserviceFacade {

	Set<String> getAllRoles(User loggedInUser);

	Set<String> getAllPrivileges(User loggedInUser);
	
	Set<String> getAllPrivileges(User loggedInUser, String group);

	boolean hasPrivilege(User loggedInUser, String privilege);

	boolean hasAllPrivileges(User loggedInUser, List<String> privileges);

}
