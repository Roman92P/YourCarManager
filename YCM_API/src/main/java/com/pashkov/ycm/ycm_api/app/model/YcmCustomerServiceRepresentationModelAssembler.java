package com.pashkov.ycm.ycm_api.app.model;

import com.pashkov.ycm.ycm_api.app.resource.YcmUserCustomerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class YcmCustomerServiceRepresentationModelAssembler extends RepresentationModelAssemblerSupport<YcmCustomerAppointment, YcmCustomerServiceModel> {

    @Autowired
    private YcmCustomerRepresentationModelAssembler ycmCustomerRepresentationModelAssembler;

    public YcmCustomerServiceRepresentationModelAssembler() {
        super(YcmUserCustomerResource.class, YcmCustomerServiceModel.class);
    }

    @Override
    public YcmCustomerServiceModel toModel(YcmCustomerAppointment entity) {
        YcmCustomerServiceModel ycmCustomerServiceModel = instantiateModel(entity);
        ycmCustomerServiceModel.setServiceType(entity.getServiceType());
        ycmCustomerServiceModel.setServiceDescription(entity.getServiceDescription());
        ycmCustomerServiceModel.setServicePrice(entity.getServicePrice());
        ycmCustomerServiceModel.setCurrency(entity.getCurrency());
        ycmCustomerServiceModel.setServiceAppointmentDay(entity.getServiceAppointmentDay());
        ycmCustomerServiceModel.setServiceHour(entity.getServiceHour());
        YcmCustomerModel ycmCustomerModel = ycmCustomerRepresentationModelAssembler.toModel(entity.getYcmCustomerPurchaseService());
        ycmCustomerServiceModel.setYcmCustomerPurchaseService(ycmCustomerModel);
        ycmCustomerServiceModel.setYcmShop(entity.getYcmShop());
        ycmCustomerServiceModel.setShortServiceName(entity.getShortServiceName());
        return ycmCustomerServiceModel;
    }

    @Override
    public CollectionModel<YcmCustomerServiceModel> toCollectionModel(Iterable<? extends YcmCustomerAppointment> entities) {
        CollectionModel<YcmCustomerServiceModel> ycmCustomerServiceModels = super.toCollectionModel(entities);
        return ycmCustomerServiceModels;
    }
}
