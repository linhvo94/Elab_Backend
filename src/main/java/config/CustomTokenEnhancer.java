package config;

import model.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import service.UserService;

import java.util.HashMap;
import java.util.Map;


public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    UserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Users userDB = userService.findUserByUsername(user.getUsername());


        final Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("user_roles", user.getAuthorities());
        additionalInfo.put("user", userDB);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
