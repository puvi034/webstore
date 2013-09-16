package webstore



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BookOrderController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BookOrder.list(params), model:[bookOrderInstanceCount: BookOrder.count()]
    }

    def show(BookOrder bookOrderInstance) {
        respond bookOrderInstance
    }

    def create() {
        respond new BookOrder(params)
    }

    @Transactional
    def save(BookOrder bookOrderInstance) {
        if (bookOrderInstance == null) {
            notFound()
            return
        }

        if (bookOrderInstance.hasErrors()) {
            respond bookOrderInstance.errors, view:'create'
            return
        }

        bookOrderInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bookOrderInstance.label', default: 'BookOrder'), bookOrderInstance.id])
                redirect bookOrderInstance
            }
            '*' { respond bookOrderInstance, [status: CREATED] }
        }
    }

    def edit(BookOrder bookOrderInstance) {
        respond bookOrderInstance
    }

    @Transactional
    def update(BookOrder bookOrderInstance) {
        if (bookOrderInstance == null) {
            notFound()
            return
        }

        if (bookOrderInstance.hasErrors()) {
            respond bookOrderInstance.errors, view:'edit'
            return
        }

        bookOrderInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BookOrder.label', default: 'BookOrder'), bookOrderInstance.id])
                redirect bookOrderInstance
            }
            '*'{ respond bookOrderInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(BookOrder bookOrderInstance) {

        if (bookOrderInstance == null) {
            notFound()
            return
        }

        bookOrderInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'BookOrder.label', default: 'BookOrder'), bookOrderInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bookOrderInstance.label', default: 'BookOrder'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
