package org.activiti.designer.util.editor;

import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.impl.IIndependenceSolver;

/**
 * @author Tijs Rademakers
 *
 */
public class KickstartProcessIndependenceSolver implements IIndependenceSolver {
	
  private IDiagramTypeProvider diagramTypeProvider;
  
  public KickstartProcessIndependenceSolver(IDiagramTypeProvider diagramTypeProvider) {
    this.diagramTypeProvider = diagramTypeProvider;
  }
    
  @Override
  public String getKeyForBusinessObject(Object bo) {
    return ensureKickstartProcessMemoryModel().getKeyForBusinessObject(bo);
  }

  @Override
  public Object getBusinessObjectForKey(String key) {
    return ensureKickstartProcessMemoryModel().getBusinessObjectForKey(key);
  }

  public Map<String, Object> getObjectMap() {
    return ensureKickstartProcessMemoryModel().getObjectMap();
  }

  public void setObjectMap(Map<String, Object> objectMap) {
    ensureKickstartProcessMemoryModel().setObjectMap(objectMap);
  }
  
  protected KickstartProcessMemoryModel ensureKickstartProcessMemoryModel() {
    if(diagramTypeProvider.getDiagram() == null) {
      throw new IllegalStateException("No diagram is currently active");
    }
    
    KickstartProcessMemoryModel model = ModelHandler.getKickstartProcessModel(EcoreUtil.getURI(diagramTypeProvider.getDiagram()));
    if(model == null) {
      throw new IllegalStateException("No diagram model is currently available for diagram: " + EcoreUtil.getURI(diagramTypeProvider.getDiagram()));
    }
    return model;
  }

}
