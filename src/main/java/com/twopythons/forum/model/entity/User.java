package com.twopythons.forum.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"downvotedMessages", "upvotedMessages", "downvotedThemes", "upvotedThemes"})
@Entity
@Table(name = "user")
public class User extends AbstractEntity implements UserDetails {

    private String login;
    private String password;
    private String email;
    private byte[] picture;
    private boolean isNonLocked = true;

    @OneToMany(mappedBy = "author")
    private Collection<Theme> themes;
    @OneToMany(mappedBy = "author")
    private Collection<Message> sentMessages;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany
    private Set<Message> upvotedMessages;
    @ManyToMany
    private Set<Message> downvotedMessages;
    @ManyToMany
    private Set<Theme> upvotedThemes;
    @ManyToMany
    private Set<Theme> downvotedThemes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
