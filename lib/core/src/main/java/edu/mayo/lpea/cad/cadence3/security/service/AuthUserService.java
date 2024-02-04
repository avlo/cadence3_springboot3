package edu.mayo.lpea.cad.cadence3.security.service;

import edu.mayo.lpea.cad.cadence3.security.PreExistingUserException;
import edu.mayo.lpea.cad.cadence3.security.entity.AppUserAuthUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface AuthUserService {

  boolean userExists(String userName);
  AppUserAuthUser getAppuserAuthuser(String username);

  AppUserAuthUser createUser(String username, String password) throws PreExistingUserException;

  List<AppUserAuthUser> getAllAppUsersMappedAuthUsers();

  Collection<GrantedAuthority> getGrantedAuthorities(String username);
}
