<%@ page import="webstore.Book" %>



<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="book.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${bookInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'ISBN', 'error')} required">
	<label for="ISBN">
		<g:message code="book.ISBN.label" default="ISBN" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="ISBN" required="" value="${bookInstance?.ISBN}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="book.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: bookInstance, field: 'price')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'publisher', 'error')} ">
	<label for="publisher">
		<g:message code="book.publisher.label" default="Publisher" />
		
	</label>
	<g:textField name="publisher" value="${bookInstance?.publisher}"/>
</div>

