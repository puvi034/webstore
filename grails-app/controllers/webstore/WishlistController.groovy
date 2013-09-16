package webstore



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WishlistController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Wishlist.list(params), model:[wishlistInstanceCount: Wishlist.count()]
    }

    def show(Wishlist wishlistInstance) {
        respond wishlistInstance
    }

    def create() {
        respond new Wishlist(params)
    }

    @Transactional
    def save(Wishlist wishlistInstance) {
        if (wishlistInstance == null) {
            notFound()
            return
        }

        if (wishlistInstance.hasErrors()) {
            respond wishlistInstance.errors, view:'create'
            return
        }

        wishlistInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'wishlistInstance.label', default: 'Wishlist'), wishlistInstance.id])
                redirect wishlistInstance
            }
            '*' { respond wishlistInstance, [status: CREATED] }
        }
    }

    def edit(Wishlist wishlistInstance) {
        respond wishlistInstance
    }

    @Transactional
    def update(Wishlist wishlistInstance) {
        if (wishlistInstance == null) {
            notFound()
            return
        }

        if (wishlistInstance.hasErrors()) {
            respond wishlistInstance.errors, view:'edit'
            return
        }

        wishlistInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Wishlist.label', default: 'Wishlist'), wishlistInstance.id])
                redirect wishlistInstance
            }
            '*'{ respond wishlistInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Wishlist wishlistInstance) {

        if (wishlistInstance == null) {
            notFound()
            return
        }

        wishlistInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Wishlist.label', default: 'Wishlist'), wishlistInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wishlistInstance.label', default: 'Wishlist'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
