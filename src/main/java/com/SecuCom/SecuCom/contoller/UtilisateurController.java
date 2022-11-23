package com.SecuCom.SecuCom.contoller;

import lombok.AllArgsConstructor;
import com.SecuCom.SecuCom.models.Role;
import com.SecuCom.SecuCom.models.Utilisateur;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SecuCom.SecuCom.service.UtilisateurService;


@AllArgsConstructor
@RestController
@RequestMapping("/Utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping("/addUser")
    public Utilisateur addUser(@RequestBody Utilisateur user){
        return utilisateurService.addUser(user);
    }
  /*  @PostMapping("/Seconnecter")
    public Utilisateur Seconnecter(@PathVariable String email, @PathVariable String password){
        return utilisateurService.Seconnecter(email,password);
    }*/
    //Permettre à l'utilisateur avec l'ADMIN_ROLE
  /*  @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @GetMapping("/Afficher")
    public List<Utilisateur> Afficher(){

        return utilisateurService.Afficher();
    }*/
    //methode permettant de savoir qui est connecter
  /*  @GetMapping("/profile")
    public Utilisateur profile(Principal principal){
        return utilisateurService.donnerUserByUsername(principal.getName());
    }*/
    @PostMapping("/AjouterRole")
    public Role addRole(@RequestBody Role role){
        return utilisateurService.addNewRole(role);
    }

    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody String username,@RequestBody String roleName){
        utilisateurService.addRoleToUser(username, roleName);
    }

    //Une methode permettant de rafraichir le token
    /*@GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String auhToken = request.getHeader(JWTUtil.AUTH_HEADER);
        if(auhToken != null && auhToken.startsWith(JWTUtil.PREFIX)){
            try{
                String jwt = auhToken.substring(JWTUtil.PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier = require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                UtilisateurController utilisateur = serviceUtilisateur.loadUserByUsername(username);
                String jwtAccesToken = create()
                        .withSubject(utilisateur.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+JWTUtil.EXPIRE_REFRESH_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",utilisateur.getRole().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> idToken = new HashMap<>();
                idToken.put("acces-token",jwtAccesToken);
                idToken.put("refresh-token",jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            }catch (Exception e){
                throw e;
            }
        }
        else{
            throw new RuntimeException("Refresh token required!!!");
        }
    }*/
}
