package unify32
import org.apache.commons.lang.builder.HashCodeBuilder

class AppPlayer implements Serializable {

    private static final long serialVersionUID = 1

    String username
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    App     app
    Player  player

    Date dateCreated
    Date lastUpdated

    @Override
    int hashCode(){
        def builder = new HashCodeBuilder()
        if (app)    builder.append(app.id)
        if (player) builder.append(player.id)
        builder.toHashCode()
    }


    @Override
    boolean equals(other){
        if(other instanceof AppPlayer){
            other.playerId == player?.id && other.appId == app?.id
        }
    }


    static AppPlayer create(App app, Player player) {
        def instance = new AppPlayer(
                enabled:            true,
                accountLocked:      false,
                accountExpired:     false,
                passwordExpired:    false,
                app:        app,
                username:   generator( (('A'..'Z')+('1'..'9')).join(), 5 ),
                player:     player)
        instance.save(flush: true, failOnError: true)
        instance
    }


    //KEY-GEN
    static generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
        }
    }

    String getGlobalName(){
        return  username +'@'+ app.bundle
    }

    static transients = ['springSecurityService', 'globalName']

    static constraints = {
        username        blank: false, unique: 'app'
        enabled         defaultValue: "true"
        accountExpired  defaultValue: "false"
        accountLocked   defaultValue: "false"
        passwordExpired defaultValue: "false"
    }

    static mapping = {
        id          composite: ['app', 'player']
    }


}
