package com.minhub.HomeBanking.configurations;

import com.minhub.HomeBanking.models.Client;
import com.minhub.HomeBanking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Bean

    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }
    @Autowired
    ClientRepository clientRepository;

            @Override
            public void init(AuthenticationManagerBuilder auth) throws Exception {

                auth.userDetailsService(findByEmail-> {

                        Client client = clientRepository.findByEmail(findByEmail);

                    if (client != null) {


                        if(client.getName().equals("admin")){
                            return new User(client.getName(),client.getPassword(),AuthorityUtils.createAuthorityList("ADMIN"));
                        }
                        return new User(client.getEmail(),client.getPassword(),AuthorityUtils.createAuthorityList("CLIENT"));
                    }

                    else {

                        throw new UsernameNotFoundException("Unknown user: " + findByEmail);

                    }

                });

            }

}
