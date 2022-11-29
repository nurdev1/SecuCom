package com.SecuCom.SecuCom.contoller;

import com.SecuCom.SecuCom.models.Role;
import com.SecuCom.SecuCom.models.Utilisateur;
import com.SecuCom.SecuCom.service.UtilisateurService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@AllArgsConstructor
@RestController

@RequestMapping("/Utilisateur")
public class UtilisateurController {

    @Autowired
    private final UtilisateurService utilisateurService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public Utilisateur addUser(@RequestBody Utilisateur user){

        return utilisateurService.addUser(user);
    }
    @RequestMapping("/**")
    @RolesAllowed("USER")
    public String getUser(Authentication authentication){
        return "welcome "+authentication.getName();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/AjouterRole")
    public Role addRole(@RequestBody Role role){

        return utilisateurService.addNewRole(role);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody String username,@RequestBody String roleName){
        utilisateurService.addRoleToUser(username, roleName);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/Afficher")
    public List<Utilisateur> Afficher(){
        return utilisateurService.Afficher();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/seconnecter/{username}/{password}")
    public String Seconnecter( @PathVariable String username, @PathVariable String password){
        return utilisateurService.seConnecter(username,password);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/modifier")
    public String modifierUtilisateur(@RequestBody Utilisateur utilisateur){
        return utilisateurService.modifierUtilisateur(utilisateur);

    }
    //Une methode permettant de rafraichir le token
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String authToken = request.getHeader("Autorization");
         if(authToken!=null && authToken.startsWith("Bearer ")){
            try {
                String jwt=authToken.substring(7);
                Algorithm algorithm= Algorithm.HMAC256("mySecret1234");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT= jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
               Utilisateur utilisateur = utilisateurService.donnerUserByUsername(username);
               String jwtAccessToken = JWT.create()
                       .withSubject(utilisateur.getUsername())
                       .withExpiresAt(new Date((System.currentTimeMillis()*1+60*1000)))
                       .withIssuer(request.getRequestURL().toString())
                       .withClaim("roles", utilisateur.getRole().stream().map(r ->
                               r.getRolename()).collect(Collectors.toList()))
                       .sign(algorithm);
                Map<String,String> idToken = new HashMap<>();
                idToken.put("access-token",jwtAccessToken);
                idToken.put("refresh-token",jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);
            }catch (Exception e){
                throw e;

            }
        }
        else {
            throw new RuntimeException("refresh token requise!!!");
        }

    }

    }
