package unify32

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Player implements Serializable {

    private static final long serialVersionUID = 1

    static hasMany =    [
            appPlayer: AppPlayer
    ]

    String  ref
    String  playername
    String  email

    Date    dateCreated
    Date    lastUpdated

    String getFirstName(){
        playername?(playername.tokenize(' ').first()):null
    }

    String getLastName(){
        def first = playername?(playername.tokenize(' ').first()):null
        def last =  playername?(playername.tokenize(' ').last()):null
        if(last!=null && last!=first){
            return last
        }else{
            return ''
        }
    }

    static transients = ['firstName', 'lastName']


    static constraints = {
        email           nullable: true, defaultValue: null, email: true
    }

    static mapping = {

    }


}
