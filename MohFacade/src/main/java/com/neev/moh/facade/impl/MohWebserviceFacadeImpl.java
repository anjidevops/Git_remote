package com.neev.moh.facade.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.neev.moh.facade.MohWebserviceFacade;
import com.neev.moh.model.Privilege;
import com.neev.moh.model.Role;
import com.neev.moh.model.User;

@Service
public class MohWebserviceFacadeImpl implements MohWebserviceFacade {

	@Override
	public Set<String> getAllRoles(User loggedInUser) {
		Set<String> roleSet = new HashSet<String>();
		if (loggedInUser != null) {
			Set<Role> roles = loggedInUser.getRoles();
			for (Role role : roles) {
				roleSet.add(role.getName());
			}
		}
		return roleSet;
	}

	@Override
	public Set<String> getAllPrivileges(User loggedInUser) {
		Set<String> privilegeSet = new HashSet<String>();
		if (loggedInUser != null) {
			Set<Role> roles = loggedInUser.getRoles();
			for (Role role : roles) {
				Set<Privilege> privileges = role.getPrivileges();
				for (Privilege privilege : privileges) {
					privilegeSet.add(privilege.getName());
				}
			}
		}
		return privilegeSet;
	}
	
	@Override
	public Set<String> getAllPrivileges(User loggedInUser, String group) {
		Set<String> privilegeSet = new HashSet<String>();
		if (loggedInUser != null) {
			Set<Role> roles = loggedInUser.getRoles();
			for (Role role : roles) {
				Set<Privilege> privileges = role.getPrivileges();
				for (Privilege privilege : privileges) {
					if(group.equals(privilege.getGroup())) {
						privilegeSet.add(privilege.getName());
					}
				}
			}
		}
		return privilegeSet;
	}

	@Override
	public boolean hasPrivilege(User loggedInUser, String privilege) {
		Set<String> privilegeSet = getAllPrivileges(loggedInUser);
		boolean privilegeFound = false;
		if (privilegeSet != null && !privilegeSet.isEmpty() && privilegeSet.contains(privilege)) {
			privilegeFound = true;
		}
		return privilegeFound;
	}

	@Override
	public boolean hasAllPrivileges(User loggedInUser, List<String> privileges) {
		Set<String> privilegeSet = getAllPrivileges(loggedInUser);
		boolean privilegeFound = true;
		if (privilegeSet != null && !privilegeSet.isEmpty()) {
			for (String privilegeString : privileges) {
				if (!privilegeSet.contains(privilegeString)) {
					privilegeFound = false;
					break;
				}
			}
		} else {
			privilegeFound = false;
		}
		return privilegeFound;
	}

}
