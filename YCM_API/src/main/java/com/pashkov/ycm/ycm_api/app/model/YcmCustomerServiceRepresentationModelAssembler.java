package com.pashkov.ycm.ycm_api.app.model;

import com.pashkov.ycm.ycm_api.app.resource.YcmUserCustomerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class YcmCustomerServiceRepresentationModelAssembler extends RepresentationModelAssemblerSupport<YcmCustomerAppointment, YcmCustomerAppointmentModel> {

    @Autowired
    private YcmCustomerRepresentationModelAssembler ycmCustomerRepresentationModelAssembler;

    public YcmCustomerServiceRepresentationModelAssembler() {
        super(YcmUserCustomerResource.class, YcmCustomerAppointmentModel.class);
    }

    @Override
    public YcmCustomerAppointmentModel toModel(YcmCustomerAppointment entity) {
        YcmCustomerAppointmentModel ycmCustomerAppointmentModel = instantiateModel(entity);
        ycmCustomerAppointmentModel.setServiceType(entity.getServiceType());
        ycmCustomerAppointmentModel.setServiceDescription(entity.getServiceDescription());
        ycmCustomerAppointmentModel.setServicePrice(entity.getServicePrice());
        ycmCustomerAppointmentModel.setCurrency(entity.getCurrency());
        ycmCustomerAppointmentModel.setServiceAppointmentDay(entity.getServiceAppointmentDay());
        ycmCustomerAppointmentModel.setServiceHour(entity.getServiceHour());
        YcmCustomerModel ycmCustomerModel = ycmCustomerRepresentationModelAssembler.toModel(entity.getYcmCustomer());
        ycmCustomerAppointmentModel.setYcmCustomer(ycmCustomerModel);
        ycmCustomerAppointmentModel.setYcmShopName(entity.getYcmShop().getShopName());
        ycmCustomerAppointmentModel.setShortServiceName(entity.getShortServiceName());
        return ycmCustomerAppointmentModel;
    }

    @Override
    public CollectionModel<YcmCustomerAppointmentModel> toCollectionModel(Iterable<? extends YcmCustomerAppointment> entities) {
        CollectionModel<YcmCustomerAppointmentModel> ycmCustomerServiceModels = super.toCollectionModel(entities);
        return ycmCustomerServiceModels;
    }
}
