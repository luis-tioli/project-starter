package unify32

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes='bundle')
class App implements Serializable {

    private static final long serialVersionUID = 1

    static hasMany   = [appPlayer: AppPlayer]

    String  id
    String  name
    String  bundle
    String  logo
    Date    dateCreated
    Date    lastUpdated
    int     buildNumber = 0

    static mapping = {
        id              generator:  'uuid2'
    }

    static constraints = {
        buildNumber   defaultValue: "0"
        bundle        blank: false,     unique: true
        logo          nullable: true,   defaultValue: null
    }


}
