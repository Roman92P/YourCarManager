package com.pashkov.ycm.ycm_api.YCM_API.app.entity;

import com.pashkov.ycm.ycm_api.YCM_API.app.resource.YcmUserCustomerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class YcmCustomerServiceRepresentationModelAssembler extends RepresentationModelAssemblerSupport<YcmCustomerService, YcmCustomerServiceModel> {

    @Autowired
    private YcmCustomerRepresentationModelAssembler ycmCustomerRepresentationModelAssembler;

    public YcmCustomerServiceRepresentationModelAssembler() {
        super(YcmUserCustomerResource.class, YcmCustomerServiceModel.class);
    }

    @Override
    public YcmCustomerServiceModel toModel(YcmCustomerService entity) {
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
        return ycmCustomerServiceModel;
    }

    @Override
    public CollectionModel<YcmCustomerServiceModel> toCollectionModel(Iterable<? extends YcmCustomerService> entities) {
        CollectionModel<YcmCustomerServiceModel> ycmCustomerServiceModels = super.toCollectionModel(entities);
        return ycmCustomerServiceModels;
    }
}
