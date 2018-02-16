package com.neev.moh.web.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.neev.moh.facade.UserFacade;
import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;
import com.neev.moh.model.Privilege;
import com.neev.moh.model.Role;
import com.neev.moh.model.User;

@Repository
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final MohLogger logger = MohLogFactory.getLoggerInstance(CustomUserDetailsService.class.getName());

    @Autowired
    private UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    	logger.log(MohLogger.INFO, "loadUserByUsername() = " + name);
        User user = userFacade.getUserByEmail(name);
        if(user == null){
        	throw new UsernameNotFoundException("No user found");
        }
        logger.log(MohLogger.INFO, "loadUserByUsername() = " + user);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            for (Privilege privilege : role.getPrivileges()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        UserDetails userDetails = new CustomUserDetails(user, grantedAuthorities);
        return userDetails;
    }
}
