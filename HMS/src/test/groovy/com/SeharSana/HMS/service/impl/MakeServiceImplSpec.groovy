//package com.project.carworkshop.service.impl
//
//import com.project.carworkshop.entity.Make
//import com.project.carworkshop.repository.MakeRepository
//import spock.lang.Specification
//
//class MakeServiceImplSpec extends Specification
//{
//    def makeRepo = Mock(MakeRepository.class)
//    def makeService = new MakeServiceImpl(makeRepo)
//
//  def "verify getMake() returns list of MakeMode for makeId"()
//  {
//      given:
//      def make = new Make(id: 1L,makeName: "Honda")
//      makeRepo.findMakeById(_) >> make
//      when:
//      def response = makeService.getMake(1L)
//
//      then:
//      response != null
//      response.get(0).makeId == make.getId()
//      response.get(0).getMakeName() == make.getMakeName()
//  }
//
//    def "verify getMake() returns null for makeId"()
//    {
//        given:
//        def make = new Make(id: 1L,makeName: "Honda")
//        makeRepo.findMakeById(_) >> null
//        when:
//        def response = makeService.getMake(1L)
//
//        then:
//        response != null
//        response.size()==0
//    }
//}
