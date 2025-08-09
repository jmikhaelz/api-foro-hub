package mx.alura.forohub.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.alura.forohub.repository.UsuarioResp;

@Service
public class AutowiredUser implements UserDetailsService {
    @Autowired
    private UsuarioResp resp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return resp.findByEmail(username);
    }

}
