package io.lumify.core.model.user;

import com.altamiracorp.bigtable.model.user.ModelUserContext;
import io.lumify.core.user.Roles;
import io.lumify.core.user.User;

import java.util.*;

public class InMemoryUser implements User {
    private final String userId;
    private final String username;
    private final String displayName;
    private final String password;
    private final List<String> authorizations;
    private final int roles;

    public InMemoryUser(String username, String displayName, String password, Collection<Roles> roles, String[] authorizations) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.displayName = displayName;
        this.password = password;
        this.authorizations = new ArrayList<String>();
        this.roles = Roles.toBits(roles);
        Collections.addAll(this.authorizations, authorizations);
    }

    @Override
    public String getUserId() {
        return this.userId;
    }

    @Override
    public ModelUserContext getModelUserContext() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public UserType getUserType() {
        return UserType.USER;
    }

    @Override
    public String getUserStatus() {
        throw new RuntimeException("not implemented");
    }

    public Set<Roles> getRoles() {
        return Roles.toSet(this.roles);
    }

    public String[] getAuthorizations() {
        return authorizations.toArray(new String[this.authorizations.size()]);
    }
}