<%@ page import="webstore.BookOrder" %>



<div class="fieldcontain ${hasErrors(bean: bookOrderInstance, field: 'book', 'error')} required">
	<label for="book">
		<g:message code="bookOrder.book.label" default="Book" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="book" name="book.id" from="${webstore.Book.list()}" optionKey="id" required="" value="${bookOrderInstance?.book?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookOrderInstance, field: 'quantity', 'error')} required">
	<label for="quantity">
		<g:message code="bookOrder.quantity.label" default="Quantity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="quantity" type="number" value="${bookOrderInstance.quantity}" required=""/>
</div>

