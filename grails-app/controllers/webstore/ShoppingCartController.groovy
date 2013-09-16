package webstore



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ShoppingCartController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ShoppingCart.list(params), model:[shoppingCartInstanceCount: ShoppingCart.count()]
    }

    def show(ShoppingCart shoppingCartInstance) {
        respond shoppingCartInstance
    }

    def create() {
        respond new ShoppingCart(params)
    }

    @Transactional
    def save(ShoppingCart shoppingCartInstance) {
        if (shoppingCartInstance == null) {
            notFound()
            return
        }

        if (shoppingCartInstance.hasErrors()) {
            respond shoppingCartInstance.errors, view:'create'
            return
        }

        shoppingCartInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shoppingCartInstance.label', default: 'ShoppingCart'), shoppingCartInstance.id])
                redirect shoppingCartInstance
            }
            '*' { respond shoppingCartInstance, [status: CREATED] }
        }
    }

    def edit(ShoppingCart shoppingCartInstance) {
        respond shoppingCartInstance
    }

    @Transactional
    def update(ShoppingCart shoppingCartInstance) {
        if (shoppingCartInstance == null) {
            notFound()
            return
        }

        if (shoppingCartInstance.hasErrors()) {
            respond shoppingCartInstance.errors, view:'edit'
            return
        }

        shoppingCartInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ShoppingCart.label', default: 'ShoppingCart'), shoppingCartInstance.id])
                redirect shoppingCartInstance
            }
            '*'{ respond shoppingCartInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ShoppingCart shoppingCartInstance) {

        if (shoppingCartInstance == null) {
            notFound()
            return
        }

        shoppingCartInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ShoppingCart.label', default: 'ShoppingCart'), shoppingCartInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCartInstance.label', default: 'ShoppingCart'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
