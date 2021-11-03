package starter

import unify32.App
import unify32.AppPlayer
import unify32.Player

class BootStrap {

    def init = { servletContext ->

        App app = App.findByBundle('br.com.muvedigital.homolog.unify')
        if (!app) {
            app = new App(
                    name: 'app1',
                    bundle: 'br.com.muvedigital.homolog.unify'
            ).save()
        }

        Player player = Player.findByRef('playerone')
        if (!player) {
            player = new Player(
                    ref: 'PLAYER-1',
                    enabled: true,
                    accountExpired: false,
                    accountLocked: false,
                    passwordExpired: false,
                    playername: 'Player One',
                    email: 'playerone@muvedigital.com.br',
            ).save()
        }

        //register dev player to access app
        AppPlayer appPlayer = AppPlayer.findByAppAndPlayer(app, player)
        if (!appPlayer) {
            appPlayer = new AppPlayer(
                    active: true,
                    enabled: true,
                    accountExpired: false,
                    accountLocked: false,
                    passwordExpired: false,
                    app: app,
                    player: player,
                    username: 'playerone'
            ).save()
        }


    }


    def destroy = {
    }
}
