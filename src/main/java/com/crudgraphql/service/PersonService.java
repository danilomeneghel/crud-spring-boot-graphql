package com.crudgraphql.service;

import java.util.List;
import java.util.Optional;

import com.crudgraphql.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudgraphql.exception.GraphQLException;
import com.crudgraphql.model.Person;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @GraphQLQuery( name = "findAllPerson" )
    public List< Person > findAll() {
        return repository.findAll();
    }

    @GraphQLQuery( name = "findByIdPerson" )
    public Optional< Person > findById( @GraphQLArgument( name = "id" ) final Long id ) {
		Optional< Person > p = this.repository.findById( id );

		if( p.isEmpty() ) {
			throw new GraphQLException( "person_not_found", new StringBuilder( "Não existe a pessoa com o id informado: " ).append( id ).toString() );
		} else {
			return this.repository.findById( id );
		}
    }

    @GraphQLMutation( name = "createPerson" )
    public Person create( @GraphQLArgument( name = "person" ) final Person person ) {
        return this.repository.save( person );
    }

    @GraphQLMutation( name = "updatePerson" )
    public Person update( @GraphQLArgument( name = "person" ) final Person person ) {
        Optional< Person > p = this.repository.findById( person.getId() );

        if( p.isEmpty() ) {
            throw new GraphQLException( "person_not_found",
                    new StringBuilder( "Não existe a pessoa com o id informado: " ).append( person.getId() ).toString() );
        } else {
            p.get().setAge( person.getAge() );
            p.get().setName( person.getName() );
            p.get().setGenre( person.getGenre() );
            p.get().setDtBirth( person.getDtBirth() );
            return this.repository.save( person );
        }
    }

    @GraphQLMutation( name = "deletePerson" )
    public void delete( @GraphQLArgument( name = "id" ) final Long id ) {
        Optional< Person > p = this.repository.findById( id );

        if( p.isEmpty() ) {
            throw new GraphQLException( "person_not_found", new StringBuilder( "Não existe a pessoa com o id informado: " ).append( id ).toString() );
        } else {
            this.repository.deleteById( id );
        }
    }

}